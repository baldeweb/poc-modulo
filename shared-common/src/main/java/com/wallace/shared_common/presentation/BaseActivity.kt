package com.wallace.shared_common.presentation

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wallace.shared_common.databinding.ActivityBaseBinding
import com.wallace.shared_common.presentation.extension.observeNonNull
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.KoinComponent
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityBaseBinding
    protected val viewModel: T by lazy { getViewModel(clazz = viewModelClass()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        super.setContentView(binding.root)

        initViewModels()
    }

    override fun setContentView(view: View?) {
        binding.fmlBaseLayout.addView(view)
    }

    protected fun showLoading() {
        if (binding.pgbLoading.visibility == GONE)
            binding.pgbLoading.visibility = VISIBLE
    }

    protected fun dismissLoading() {
        if (binding.pgbLoading.visibility == VISIBLE)
            binding.pgbLoading.visibility = GONE
    }

    private fun initViewModels() {
        viewModel.errorResponse.observeNonNull(this) {
            AlertDialog.Builder(this).apply {
                setTitle("Erro Inesperado")
                setMessage("[${it.httpCode}] - ${it.throwable?.message ?: "Erro ao pegar as informações"}")
                setPositiveButton("Ok") { _, _ -> }
            }.show()
        }

        viewModel.shouldShowLoading.observeNonNull(this) {
            if (it) showLoading() else dismissLoading()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<T> {
        return try {
            ((javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<T>).kotlin
        } catch (e: Exception) {
            return Any() as KClass<T>
        }
    }
}