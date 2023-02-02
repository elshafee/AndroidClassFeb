class FlutterDeveloper : Employee(), Contract, NDA {

    override fun netSalary() {
        println("Your net salary is 15000 EGP")
    }

    override fun benifits() {
        println("You can get 15% benifits every year on your salary")
    }

    override fun daysOff() {
        println("You have 25 days off per year")
    }

    override fun workingHours() {
        println("You have to work 30 hrs per week")
    }

    override fun bandNumberOne() {
        super<Contract>.bandNumberOne()
    }

    override fun bandNumberTwo() {
        println("Band number 2")
    }

    override fun bandNumberThree() {
        println("Band number 3")
    }

    override fun bandNumberTFour() {
        println("Band number 4")
    }

    override fun doNotShareContentWithOther() {
        println("don't share with others")
    }
}