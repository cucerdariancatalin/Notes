/*
 * Copyright (C) 2020 Arseniy Graur
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.argraur.notes.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.card.MaterialCardView

import me.argraur.notes.R
import me.argraur.notes.entities.Note
import me.argraur.notes.screens.NoteActivity

const val NOTE_TITLE = "me.argraur.notes.NOTE_TITLE"
const val NOTE_VALUE = "me.argraur.notes.NOTE_VALUE"
const val NOTE_TIME = "me.argraur.notes.NOTE_TIME"
const val NOTE_COLOR = "me.argraur.notes.NOTE_COLOR"

class NotesAdapter(private val notes: Array<Note>, private val activity: Activity): RecyclerView.Adapter<NotesAdapter.NotesHolder>() {
    class NotesHolder(val cardView: MaterialCardView): RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false) as MaterialCardView
        return NotesHolder(cardView)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val title = notes[position].getTitle()
        val value = notes[position].getValue()
        val time = notes[position].getTime()
        val color = notes[position].getColor()
        ((holder.cardView[0] as ConstraintLayout)[0] as TextView).text = title
        ((holder.cardView[0] as ConstraintLayout)[1] as TextView).text = value
        holder.cardView.setCardBackgroundColor(notes[position].getColor())
        holder.cardView.setOnClickListener {
            activity.startActivity(Intent(activity, NoteActivity::class.java).apply {
                putExtra(NOTE_TITLE, title)
                putExtra(NOTE_VALUE, value)
                putExtra(NOTE_TIME, time)
                putExtra(NOTE_COLOR, color)
            })
        }
    }

    override fun getItemCount() = notes.size
}