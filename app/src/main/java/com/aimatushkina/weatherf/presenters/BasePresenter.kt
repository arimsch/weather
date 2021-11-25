package com.aimatushkina.weatherf.presenters

import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {
//    данные возвращаются не мгновенно, их нужно прослушивать
    abstract fun enable()

}