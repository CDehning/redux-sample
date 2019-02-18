package de.dehning.reduxsample.defuse.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import de.dehning.reduxsample.R
import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainDispatcher
import de.dehning.reduxsample.defuse.domain.MainState
import de.dehning.reduxsample.defuse.domain.MainStore
import de.dehning.reduxsample.mvp.StateView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.temporal.ChronoUnit

/**
 * The main screen of the app. Displays a bomb that needs to be defused.
 */
class MainActivity : AppCompatActivity(), StateView<MainState> {

    private lateinit var presenter: MainPresenter

    private val dispatcher = MainDispatcher()
    private val store = MainStore(dispatcher)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(
            observable = store.observable,
            mainThreadScheduler = AndroidSchedulers.mainThread()
        )

        lockDigit0.setOnClickListener { dispatcher.dispatch(MainAction.DigitLockIncrease(0)) }
        lockDigit1.setOnClickListener { dispatcher.dispatch(MainAction.DigitLockIncrease(1)) }
        lockDigit2.setOnClickListener { dispatcher.dispatch(MainAction.DigitLockIncrease(2)) }
        lockDigit3.setOnClickListener { dispatcher.dispatch(MainAction.DigitLockIncrease(3)) }

        reset.setOnClickListener { dispatcher.dispatch(MainAction.ResetBomb) }
        countDownReset.setOnClickListener { dispatcher.dispatch(MainAction.ResetCountdown) }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    @SuppressLint("SetTextI18n")
    override fun updateState(state: MainState) {
        when (state) {
            is MainState.Active -> {
                updateLockDigits(state.digits.map { it.toString() })

                val millisBetween = ChronoUnit.MILLIS.between(ZonedDateTime.now(), state.countDownEnd)
                countDown.text = String.format("%.3f sec", millisBetween / 1000.0f)

                countDownReset.text = "reset countdown"
                countDownReset.isEnabled = true

                reset.visibility = GONE
            }
            MainState.Boom -> {
                updateLockDigits(listOf("X", "X", "X", "X"))

                countDown.text = "Boom!"
                countDownReset.isEnabled = false

                reset.visibility = VISIBLE
            }
            MainState.Defused -> {
                updateLockDigits(listOf("Y", "E", "A", "H"))

                countDown.text = "well done!"
                countDownReset.isEnabled = false

                reset.visibility = VISIBLE
            }
        }
    }

    private fun updateLockDigits(digits: List<String>) {
        lockDigit0.text = digits[0]
        lockDigit1.text = digits[1]
        lockDigit2.text = digits[2]
        lockDigit3.text = digits[3]
    }
}
