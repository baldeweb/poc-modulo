package com.wallace.shared_common.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.install.model.InstallStatus.*
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus.CANCELING
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION
import com.wallace.shared_common.databinding.ActivityBaseBinding
import com.wallace.shared_common.presentation.constants.OtherConstants.CONFIRMATION_REQUEST_CODE
import com.wallace.shared_common.presentation.constants.NavigationActionsConstants.DETAIL_POKEMON_ACTION_NAME
import com.wallace.shared_common.presentation.constants.ModuleNameConstants.DETAIL_PRIVATE_MODULE_NAME
import com.wallace.shared_common.presentation.extension.StringUtils.logV
import com.wallace.shared_common.presentation.extension.observeNonNull
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.component.KoinComponent
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(), KoinComponent {
    private lateinit var splitManager: SplitInstallManager
    private lateinit var binding: ActivityBaseBinding
    protected val viewModel: T by lazy { getViewModel(clazz = viewModelClass()) }

    private val stateListener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            PENDING -> {
                logV("stateListener >> PENDING")
            }
            DOWNLOADING -> {
                logV("stateListener >> DOWNLOADING")
                showLoading()
            }
            DOWNLOADED -> {
                logV("stateListener >> DOWNLOADED")
                dismissLoading()
            }
            INSTALLING -> {
                logV("stateListener >> INSTALLING")
                showLoading()
            }
            INSTALLED -> {
                dismissLoading()
                val moduleName = (state.moduleNames() as ArrayList<String>).first()
                logV("stateListener >> INSTALLED >> $moduleName")
                onSuccessSplitModuleInstalled(moduleName)
            }
            REQUIRES_USER_CONFIRMATION -> {
                logV("stateListener >> REQUIRES_USER_CONFIRMATION")
                dismissLoading()
                splitManager.startConfirmationDialogForResult(
                    state,
                    this,
                    CONFIRMATION_REQUEST_CODE
                )
            }
            CANCELING -> {
                logV("stateListener >> CANCELING")
                showLoading()
            }
            FAILED, CANCELED -> {
                logV("stateListener >> FAILED | CANCELED")
                dismissLoading()
            }
        }
    }

    private fun onSuccessSplitModuleInstalled(moduleNames: String) {
        when (moduleNames) {
            DETAIL_PRIVATE_MODULE_NAME -> {
                startActivity(
                    Intent(DETAIL_POKEMON_ACTION_NAME).apply {
                        setPackage(packageName)
                        addFlags(FLAG_ACTIVITY_NEW_TASK)
                    }
                )
            }
            else -> {
                return
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        super.setContentView(binding.root)
        splitManager = SplitInstallManagerFactory.create(this)

        initViewModels()
        dynamicFeature()
    }

    override fun onResume() {
        splitManager.registerListener(stateListener)
        super.onResume()
    }

    override fun onPause() {
        splitManager.unregisterListener(stateListener)
        super.onPause()
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

    private fun dynamicFeature() {
        val request = SplitInstallRequest
            .newBuilder()
            .addModule("detail-private")
            .addModule("detail-public")
            .build()

        val splitInstallManager = SplitInstallManagerFactory.create(this)
        splitInstallManager
            .startInstall(request)
            .addOnSuccessListener {
                logV("splitInstallManager >> addOnSuccessListener")
            }
            .addOnFailureListener {
                logV("splitInstallManager >> addOnFailureListener")
            }
            .addOnCompleteListener {
                logV("splitInstallManager >> addOnCompleteListener")
            }

        splitInstallManager.registerListener(stateListener)
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.onActivityResult(requestCode, resultCode, data)
    }
}