package id.ergun.mymoviedb.core.util.testing

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by alfacart on 22/11/20.
 */
class EspressoIdlingResource {

    companion object {
        private const val RESOURCE = "GLOBAL"

        private val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

        fun increment() {
            mCountingIdlingResource.increment()
        }

        fun decrement() {
            mCountingIdlingResource.decrement()
        }

        fun getIdlingResource(): IdlingResource {
            return mCountingIdlingResource
        }
    }
}