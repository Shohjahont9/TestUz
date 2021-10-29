package uz.lars_lion.test.ui.main.root

import androidx.lifecycle.Lifecycle
import com.badoo.mvicore.android.lifecycle.createDestroy
import com.badoo.ribs.annotation.ExperimentalApi
import com.badoo.ribs.clienthelper.interactor.Interactor
import com.badoo.ribs.core.modality.BuildParams
import com.badoo.ribs.routing.source.backstack.BackStack
import uz.lars_lion.test.ui.main.root.routing.RootRouter.Configuration

@ExperimentalApi
internal class RootInteractor(
    buildParams: BuildParams<*>,
    private val backStack: BackStack<Configuration>,
) : Interactor<Root, Nothing>(
    buildParams = buildParams
) {

    override fun onCreate(nodeLifecycle: Lifecycle) {
        nodeLifecycle.createDestroy {
//            bind(authDataSource.authUpdates to authStateConsumer)
//            bind(networkErrors to networkErrorsConsumer)
        }
    }

//    private val authStateConsumer = Consumer<AuthState> { state ->
//        popLoginIfNeeded()
//        when (state) {
//            is AuthState.Unauthenticated -> backStack.replace(LoggedOut)
//            is AuthState.Anonymous, is AuthState.Authenticated -> backStack.replace(LoggedIn)
//        }
//    }
//
//    private fun popLoginIfNeeded() {
//        if (backStack.activeConfiguration is Login) {
//            backStack.popBackStack()
//        }
//    }
//
//    private val networkErrorsConsumer = Consumer<NetworkError> { error ->
//        when (error) {
//            is NetworkError.Unauthorized -> backStack.push(Login)
//        }
//    }
}
