package codehz.c4droidhelper.model.Kii

import android.util.Log
import codehz.c4droidhelper.model.DataSource
import com.kii.cloud.storage.Kii
import com.kii.cloud.storage.KiiObject
import com.kii.cloud.storage.callback.KiiQueryCallBack
import com.kii.cloud.storage.query.KiiQueryResult
import java.util.ArrayList

object RepoSource : DataSource<Repo>() {
    val queryLists: MutableList<Repo> = ArrayList<Repo>()

    override fun getCount(): Int {
        return queryLists.size()
    }

    override fun get(pos: Int): Repo {
        return queryLists[pos]
    }

    override fun flush() {
        doQuery()
    }

    private fun doQuery() {
        Kii.bucket("Repositories").query(object : KiiQueryCallBack<KiiObject>() {
            override fun onQueryCompleted(token: Int, result: KiiQueryResult<KiiObject>?, exception: Exception?) {
                Log.w("RepoSource", "get" + result?.getResult()?.size())
                if (exception != null) {
                    Log.w("RepoSource", "exception" + exception)
                    callback(exception)
                    return
                }
                for (item in result!!.getResult())
                    queryLists add Repo(
                            item getString "title" ?: "",
                            item getString "preview" ?: "",
                            item getString "copyright" ?: "",
                            item getString "category" ?: "",
                            item getString "content" ?: "")
                callback(null)
            }
        }, null)
    }
}
