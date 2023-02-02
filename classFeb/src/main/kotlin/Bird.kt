abstract class Bird {

   open fun fly(){
        println("Bird is flying ..")
    }
}

interface animal {
    fun eat(){
        println("can eat anything")
    }
}



class Duck : Bird(),animal{

    override fun fly(){
        println("Duck can't flying")
    }

}