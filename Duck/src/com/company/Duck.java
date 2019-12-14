package com.company;

public class Duck {

    public Duck(){

    }

    public void display(){}
    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;

    public void fly(){
        flyBehavior.fly();
    }

    public void setFlyBehavior(FlyBehavior mflyBehavior){
        flyBehavior = mflyBehavior;
    }
    public void setQuackBehavior(QuackBehavior mquackBehavior){}


}
