package com.example.quiz

import com.google.gson.annotations.SerializedName

data class Modeldata(

	@field:SerializedName("result")
	val result: Result? = null,

	@field:SerializedName("code")
	val code: Int? = null
)

data class OptionsItem(

	@field:SerializedName("lable")
	val lable: String? = null,

	@field:SerializedName("key")
	val key: Int? = null
)

data class Result(

	@field:SerializedName("timeInMinutes")
	val timeInMinutes: Int? = null,

	@field:SerializedName("questions")
	val questions: List<QuestionsItem?>? = null
)

data class QuestionsItem(

	@field:SerializedName("options")
	val options: List<OptionsItem?>? = null,

	@field:SerializedName("lable")
	val lable: String? = null,

	@field:SerializedName("correct_answers")
	val correctAnswers: List<Int?>? = null
)
