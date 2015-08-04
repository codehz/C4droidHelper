package codehz.c4droidhelper.model

abstract class DataSource<T> {
    public var callback: ((Exception?) -> Unit) = { x -> Unit }
    abstract fun getCount(): Int
    abstract fun get(pos: Int): T
    abstract fun flush()
}
