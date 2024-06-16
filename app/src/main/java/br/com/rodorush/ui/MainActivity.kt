package br.com.rodorush.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.rodorush.R
import br.com.rodorush.databinding.ActivityMainBinding
import br.com.rodorush.model.domain.ApiError
import br.com.rodorush.model.domain.LanguageList.Language
import br.com.rodorush.model.livedata.TranslatorLiveData
import br.com.rodorush.ui.viewmodel.TranslatorViewModel

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val tvm: TranslatorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
        setSupportActionBar(amb.mainTb.apply { title = context.getString(R.string.app_name) })

        var fromLanguage = ""
        var toLanguage = ""

        val languagesAdapter = ArrayAdapter<Language>(this, android.R.layout.simple_list_item_1, mutableListOf())
        with(amb) {
            languageFromActv.apply {
                setAdapter(languagesAdapter)
                setOnItemClickListener { parent, _, position, _ ->
                    val selectedLanguage = parent.getItemAtPosition(position) as Language
                    fromLanguage = selectedLanguage.language
                }
            }
            languageToActv.apply {
                setAdapter(languagesAdapter)
                setOnItemClickListener { parent, _, position, _ ->
                    val selectedLanguage = parent.getItemAtPosition(position) as Language
                    toLanguage = selectedLanguage.language
                }
            }
            translateBt.setOnClickListener {
                tvm.translate(fromEt.text.toString(), fromLanguage, toLanguage)
            }
        }

        TranslatorLiveData.languageListLiveData.observe(this) { languageList ->
            languagesAdapter.clear()
            languagesAdapter.addAll(languageList.languages.sortedBy { it.name })
            languagesAdapter.notifyDataSetChanged()
            languagesAdapter.getItem(0)?.also { from ->
                amb.languageFromActv.setText(from.name, false)
                fromLanguage = from.language
            }
            languagesAdapter.getItem(languagesAdapter.count - 1)?.also { to ->
                amb.languageToActv.setText(to.name, false)
                toLanguage = to.language
            }
        }

        TranslatorLiveData.translationResultLiveData.observe(this) { translationResult ->
            with(amb) {
                translationResult.data.translations.translatedText.also {
                    toEt.setText(it)
                }
            }
        }

        tvm.getLanguages()

        tvm.apiError.observe(this) { apiError ->
            showErrorDialog(apiError)
        }
    }

    private fun showErrorDialog(apiError: ApiError) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.error))
            .setMessage(getString(R.string.error_code_message, apiError.code, apiError.message))
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}