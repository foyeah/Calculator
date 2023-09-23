package com.example.calculator

import androidx.annotation.IdRes

enum class Digits(var text: String, @IdRes var resourceId: Int){
    Zero("0", R.id.Button_Zero),
    One("1", R.id.Button_One),
    Two("2", R.id.Button_Two),
    Three("3", R.id.Button_Three),
    Four("4", R.id.Button_Four),
    Five("5", R.id.Button_Five),
    Six("6", R.id.Button_Six),
    Seven("7", R.id.Button_Seven),
    Eight("8", R.id.Button_Eight),
    Nine("9", R.id.Button_Nine);
}