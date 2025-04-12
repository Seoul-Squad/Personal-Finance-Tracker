package main.java.utils

 fun<T> check(
    title: String,
    result: T,
    expectedResult: T
) {
    println(
        if(result == expectedResult){
            "-Test Passed - $title"
        } else {
            "-Test Failed - $title"
        }
    )
}