package id.ergun.mymoviedb.ui.view.tvshow

import android.os.Build
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TvShowFragmentTest {

    private lateinit var fragment: TvShowFragment

    @Before
    fun setup() {
        fragment = TvShowFragment()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(fragment)
    }

}