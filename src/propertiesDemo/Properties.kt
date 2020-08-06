package propertiesDemo

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AreaDelegate<P,Q>(val width:Int, val height: Int) : ReadWriteProperty<P,Q> {
    var  map = HashMap<String,Q>()
    override fun getValue(thisRef: P, property: KProperty<*>): Q {

        if (width > 0 && height > 0)
            map.put(property.name, (height * width) as Q )
        return map[property.name] ?: 0 as Q
    }

    override fun setValue(thisRef: P, property: KProperty<*>, value: Q) {
        println("calling area Set value delegate")
        map.put(property.name,value)

    }
}

class Rectangle(var width : Int , var height : Int) {
    val area: Int?  by AreaDelegate<Rectangle,Int>(width,height)



}

fun mainprop()
{
    val rectangle =Rectangle(width = 10,height = 10)
    println(rectangle.area)

    rectangle.width = -20
    rectangle.height=20

   println(rectangle.area)
}
