package id.ergun.mymoviedb.ui.view.tvshow

import android.os.Build
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.util.FragmentTestUtil.startFragment

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class TvShowFragmentTest {

    lateinit var fragment: TvShowFragment

    @Before
    public fun setup() {
        fragment = TvShowFragment()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(fragment)
    }

}