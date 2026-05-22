package com.spheign.gamescore

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.cardview.widget.CardView

class MainActivity : ComponentActivity() {
    private val PREFS_NAME = "GameScorePrefs"
    
    private val KEY_2P_SCORE1 = "2p_score1"
    private val KEY_2P_SCORE2 = "2p_score2"
    private val KEY_3P_SCORE1 = "3p_score1"
    private val KEY_3P_SCORE2 = "3p_score2"
    private val KEY_3P_SCORE3 = "3p_score3"
    private val KEY_THREE_PLAYER = "three_player"
    
    private val INITIAL_SCORE = 100
    private val WIN_SCORE = 4
    private val FOUL_SCORE = 1

    private lateinit var prefs: SharedPreferences

    private lateinit var player1Score: TextView
    private lateinit var player1Win: Button
    private lateinit var player1Foul: Button

    private lateinit var player2Score: TextView
    private lateinit var player2Win: Button
    private lateinit var player2Foul: Button

    private lateinit var player3Card: CardView
    private lateinit var player3Score: TextView
    private lateinit var player3Win: Button
    private lateinit var player3Foul: Button

    private lateinit var resetBtn: Button
    private lateinit var modeBtn: Button

    private lateinit var selectorPanel: LinearLayout
    private lateinit var selectorTitle: TextView
    private lateinit var selectorPlayer1: Button
    private lateinit var selectorPlayer2: Button
    private lateinit var selectorPlayer3: Button
    private lateinit var selectorCancel: Button

    private lateinit var confirmPanel: LinearLayout
    private lateinit var confirmTitle: TextView
    private lateinit var confirmOk: Button
    private lateinit var confirmCancel: Button

    private var isThreePlayerMode = false
    private var pendingAction: String? = null
    private var pendingPlayer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        isThreePlayerMode = prefs.getBoolean(KEY_THREE_PLAYER, false)

        initViews()
        loadScores()
        setupClickListeners()
    }

    private fun initViews() {
        player1Score = findViewById(R.id.player1_score)
        player1Win = findViewById(R.id.player1_win)
        player1Foul = findViewById(R.id.player1_foul)

        player2Score = findViewById(R.id.player2_score)
        player2Win = findViewById(R.id.player2_win)
        player2Foul = findViewById(R.id.player2_foul)

        player3Card = findViewById(R.id.player3_card)
        player3Score = findViewById(R.id.player3_score)
        player3Win = findViewById(R.id.player3_win)
        player3Foul = findViewById(R.id.player3_foul)

        resetBtn = findViewById(R.id.reset_btn)
        modeBtn = findViewById(R.id.mode_btn)

        selectorPanel = findViewById(R.id.selector_panel)
        selectorTitle = findViewById(R.id.selector_title)
        selectorPlayer1 = findViewById(R.id.selector_player1)
        selectorPlayer2 = findViewById(R.id.selector_player2)
        selectorPlayer3 = findViewById(R.id.selector_player3)
        selectorCancel = findViewById(R.id.selector_cancel)

        confirmPanel = findViewById(R.id.confirm_panel)
        confirmTitle = findViewById(R.id.confirm_title)
        confirmOk = findViewById(R.id.confirm_ok)
        confirmCancel = findViewById(R.id.confirm_cancel)

        updateModeUI()
    }

    private fun loadScores() {
        if (isThreePlayerMode) {
            player1Score.text = prefs.getInt(KEY_3P_SCORE1, INITIAL_SCORE).toString()
            player2Score.text = prefs.getInt(KEY_3P_SCORE2, INITIAL_SCORE).toString()
            player3Score.text = prefs.getInt(KEY_3P_SCORE3, INITIAL_SCORE).toString()
        } else {
            player1Score.text = prefs.getInt(KEY_2P_SCORE1, INITIAL_SCORE).toString()
            player2Score.text = prefs.getInt(KEY_2P_SCORE2, INITIAL_SCORE).toString()
            player3Score.text = INITIAL_SCORE.toString()
        }
    }

    private fun updateModeUI() {
        if (isThreePlayerMode) {
            player3Card.visibility = View.VISIBLE
            modeBtn.text = getString(R.string.mode_2_player)
        } else {
            player3Card.visibility = View.GONE
            modeBtn.text = getString(R.string.mode_3_player)
        }
    }

    private fun setupClickListeners() {
        player1Win.setOnClickListener { handleWin(0) }
        player1Foul.setOnClickListener { handleFoul(0) }
        player2Win.setOnClickListener { handleWin(1) }
        player2Foul.setOnClickListener { handleFoul(1) }
        player3Win.setOnClickListener { handleWin(2) }
        player3Foul.setOnClickListener { handleFoul(2) }

        resetBtn.setOnClickListener { showConfirmPanel() }
        modeBtn.setOnClickListener { togglePlayerMode() }

        selectorPlayer1.setOnClickListener { handleSelectorClick(0) }
        selectorPlayer2.setOnClickListener { handleSelectorClick(1) }
        selectorPlayer3.setOnClickListener { handleSelectorClick(2) }
        selectorCancel.setOnClickListener { hideSelector() }

        confirmOk.setOnClickListener {
            resetScores()
            hideConfirmPanel()
        }
        confirmCancel.setOnClickListener { hideConfirmPanel() }
    }

    private fun showConfirmPanel() {
        confirmTitle.text = getString(R.string.confirm_reset)
        confirmPanel.visibility = View.VISIBLE
        selectorPanel.visibility = View.GONE
    }

    private fun hideConfirmPanel() {
        confirmPanel.visibility = View.GONE
    }

    private fun handleWin(playerIndex: Int) {
        if (!isThreePlayerMode) {
            val loserIndex = if (playerIndex == 0) 1 else 0
            playerWin(playerIndex, loserIndex)
        } else {
            pendingAction = "WIN"
            pendingPlayer = playerIndex
            selectorTitle.text = getString(R.string.select_loser)
            updateSelectorVisibility(playerIndex)
            selectorPanel.visibility = View.VISIBLE
            confirmPanel.visibility = View.GONE
        }
    }

    private fun handleFoul(playerIndex: Int) {
        if (!isThreePlayerMode) {
            val beneficiaryIndex = if (playerIndex == 0) 1 else 0
            playerFoul(playerIndex, beneficiaryIndex)
        } else {
            pendingAction = "FOUL"
            pendingPlayer = playerIndex
            selectorTitle.text = getString(R.string.select_beneficiary)
            updateSelectorVisibility(playerIndex)
            selectorPanel.visibility = View.VISIBLE
            confirmPanel.visibility = View.GONE
        }
    }

    private fun updateSelectorVisibility(excludePlayer: Int) {
        selectorPlayer1.visibility = if (excludePlayer == 0) View.GONE else View.VISIBLE
        selectorPlayer2.visibility = if (excludePlayer == 1) View.GONE else View.VISIBLE
        selectorPlayer3.visibility = if (excludePlayer == 2 || !isThreePlayerMode) View.GONE else View.VISIBLE
    }

    private fun handleSelectorClick(targetIndex: Int) {
        if (pendingAction == "WIN") {
            playerWin(pendingPlayer, targetIndex)
        } else if (pendingAction == "FOUL") {
            playerFoul(pendingPlayer, targetIndex)
        }
        hideSelector()
    }

    private fun hideSelector() {
        selectorPanel.visibility = View.GONE
        pendingAction = null
        pendingPlayer = 0
    }

    private fun playerWin(winnerIndex: Int, loserIndex: Int) {
        val scores = intArrayOf(
            player1Score.text.toString().toInt(),
            player2Score.text.toString().toInt(),
            player3Score.text.toString().toInt()
        )

        scores[winnerIndex] += WIN_SCORE
        scores[loserIndex] -= WIN_SCORE

        updateScores(scores)
    }

    private fun playerFoul(foulIndex: Int, beneficiaryIndex: Int) {
        val scores = intArrayOf(
            player1Score.text.toString().toInt(),
            player2Score.text.toString().toInt(),
            player3Score.text.toString().toInt()
        )

        scores[foulIndex] -= FOUL_SCORE
        scores[beneficiaryIndex] += FOUL_SCORE

        updateScores(scores)
    }

    private fun updateScores(scores: IntArray) {
        player1Score.text = scores[0].toString()
        player2Score.text = scores[1].toString()
        player3Score.text = scores[2].toString()

        saveScores(scores)
    }

    private fun saveScores(scores: IntArray) {
        val editor = prefs.edit()
        if (isThreePlayerMode) {
            editor.putInt(KEY_3P_SCORE1, scores[0])
            editor.putInt(KEY_3P_SCORE2, scores[1])
            editor.putInt(KEY_3P_SCORE3, scores[2])
        } else {
            editor.putInt(KEY_2P_SCORE1, scores[0])
            editor.putInt(KEY_2P_SCORE2, scores[1])
        }
        editor.apply()
    }

    private fun resetScores() {
        val scores = IntArray(3) { INITIAL_SCORE }
        updateScores(scores)
    }

    private fun togglePlayerMode() {
        isThreePlayerMode = !isThreePlayerMode
        updateModeUI()

        val editor = prefs.edit()
        editor.putBoolean(KEY_THREE_PLAYER, isThreePlayerMode)
        editor.apply()

        loadScores()
    }
}
