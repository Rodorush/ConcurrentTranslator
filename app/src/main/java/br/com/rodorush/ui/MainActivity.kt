package br.com.rodorush.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.rodorush.R
import br.com.rodorush.databinding.ActivityMainBinding
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

        val languagesAdapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        with(amb) {
            languageFromActv.apply {
                setAdapter(languagesAdapter)
                setOnItemClickListener { _, _, _, _ -> fromLanguage = text.toString() }
            }
            languageToActv.apply {
                setAdapter(languagesAdapter)
                setOnItemClickListener { _, _, _, _ -> toLanguage = text.toString() }
            }
            translateBt.setOnClickListener {
                tvm.translate(fromLanguage, toLanguage, toEt.text.toString(), "text")
            }
        }

        TranslatorLiveData.languageListLiveData.observe(this) { languageList ->
            val sortedLanguages =
                languageList.data.languages.sortedBy { it.language }.map { it.language }
            languagesAdapter.clear()
            languagesAdapter.addAll(sortedLanguages)
            languagesAdapter.notifyDataSetChanged()
            languagesAdapter.getItem(0)?.also { from ->
                amb.languageFromActv.setText(from, false)
                fromLanguage = from
            }
            languagesAdapter.getItem(languagesAdapter.count - 1)?.also { to ->
                amb.languageToActv.setText(to, false)
                toLanguage = to
            }
        }

        TranslatorLiveData.translationResultLiveData.observe(this) { translationResult ->
            with(amb) {
                translationResult.data.translations.first().translatedText.also {
                    toEt.setText(it)
                }
            }
        }

        tvm.getLanguages()
    }
}