package com.example.quiz.Controller

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.MainActivity
import com.example.quiz.OptionsItem
import com.example.quiz.QuestionsItem
import com.example.quiz.R
import org.w3c.dom.Text

class RvSetup(
    val mainActivity: MainActivity,
    val Data: ArrayList<QuestionsItem>,
//    val Option: ArrayList<OptionsItem>
) :
    RecyclerView.Adapter<RvSetup.Viewholder>() {


//    private var temp = arrayListOf<OptionsItem>()

    private var type: Int? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {

        var k1 = Data.get(position).options?.get(0)!!.key
        var k2 = Data.get(position).options?.get(1)!!.key
        var k3 = Data.get(position).options?.get(2)!!.key
        var k4 = Data.get(position).options?.get(3)!!.key
        var s1 = Data.get(position).options?.get(0)!!.lable
        var s2 = Data.get(position).options?.get(1)!!.lable
        var s3 = Data.get(position).options?.get(2)!!.lable
        var s4 = Data.get(position).options?.get(3)!!.lable




        holder.heading.setText(Data.get(position).lable)
        holder.optionA.text = s1.toString()
        holder.optionB.text = s2.toString()
        holder.optionC.text = s3.toString()
        holder.optionD.text = s4.toString()

        holder.Result.setOnClickListener {
            var answer = Data.get(position).correctAnswers!!.get(0)


            if (holder.optionA.isChecked) {
                type = 1
            } else if (holder.optionB.isChecked) {
                type = 2
            } else if (holder.optionC.isChecked) {
                type = 3
            } else if (holder.optionD.isChecked) {
                type = 4
            }
            if (answer == type) {
                Toast.makeText(mainActivity, "True", Toast.LENGTH_SHORT).show()
                type = 0
            } else {
                Toast.makeText(mainActivity, "False", Toast.LENGTH_SHORT).show()
            }
            /*   Toast.makeText(mainActivity, "${type}", Toast.LENGTH_SHORT).show()*/

        }




    }

    override fun getItemCount(): Int {

        return Data.size
    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var heading = itemView.findViewById<TextView>(R.id.Questions)
        var optionA = itemView.findViewById<RadioButton>(R.id.OptionA)
        var optionB = itemView.findViewById<RadioButton>(R.id.OptionB)
        var optionC = itemView.findViewById<RadioButton>(R.id.OptionC)
        var optionD = itemView.findViewById<RadioButton>(R.id.OptionD)
        var RBGroup = itemView.findViewById<RadioGroup>(R.id.RBGroup)
        var Result = itemView.findViewById<Button>(R.id.Result)
        var NumberSequence = itemView.findViewById<TextView>(R.id.NumberSequence)

    }
}