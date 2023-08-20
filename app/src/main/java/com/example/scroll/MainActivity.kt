package com.example.scroll

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState

import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainActivityViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)

        viewModel.getAnimals()

        setContent {

            //AnimalList(liveAnimals = viewModel.liveAnimals)
            AnimalList(liveAnimals = viewModel.liveAnimals)
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun AnimalList(liveAnimals: LiveData<List<Animal>>) {
       // Text(text = "${liveAnimals.value}")
        Spacer(modifier = Modifier.height(70.dp))
        val animal = liveAnimals.value
        val kewan by liveAnimals.observeAsState(initial = emptyList())
        val listState = rememberLazyGridState()
       LazyVerticalGrid(columns = GridCells.Fixed(2), content ={
           /*
           animal?.forEach(){anim->
               item {
                   Text(text = "${anim.name}")
               }
           }
            */
         itemsIndexed(kewan){ins,kewans->
             if(ins == kewan.lastIndex){
                 viewModel.getAnimals()
             }
             Log.e("indek","${ins}")
             Column(
                 Modifier
                     .height(300.dp)
                     .width(50.dp)) {
                 Text(text = "${kewans.name}")
                 Spacer(modifier = Modifier.height(200.dp))
             }
         }
         /*
           kewan?.forEachIndexed { index, animalss ->

                     if(index == kewan.lastIndex){
                         viewModel.getAnimals()
                         
                     }


                     //viewModel.getAnimals()

               Log.e("indek","${index}")
               item{
                   Column(
                       Modifier
                           .height(300.dp)
                           .width(50.dp)) {
                       Text(text = "${animalss.name}")
                       Spacer(modifier = Modifier.height(200.dp))
                   }
               }
           }
           //Log.e("lastanimal","${kewan.last().name}")

          */
       } )





        //Text(text = "hallo class animalist")
        //val animals by liveAnimals.observeAsState(initial = emptyList())
        /*
        LazyColumnForIndexed(items = animals) { index, animal ->

            if (index == animals.lastIndex) {
                viewModel.getAnimals()
            }

            ListItem(
                icon = { Text(animal.emoji) },
                text = { Text(animal.name) },
                modifier = Modifier.padding(vertical = 30.dp)
            )
        }
    }

         */
         }
}