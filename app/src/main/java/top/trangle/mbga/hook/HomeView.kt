import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog

object HomeViewHooker : YukiBaseHooker() {
    override fun onHook() {
        val BasicIndexItem = "com.bilibili.pegasus.api.model.BasicIndexItem".toClass()
        val getUri = BasicIndexItem.method { name = "getUri" }

        getUri.hook {
            after {
                val uri = result as String?
                if (uri != null && uri.isNotEmpty()) {
                    if (uri.startsWith("bilibili://story/")) {
                        result = "bilibili://video/" + uri.substringAfter("bilibili://story/")
                        YLog.debug("初始化视频信息时，竖屏转横屏成功")
                    }
                }
                YLog.debug("item: ${reflectionToString(instance)}")
            }
        }
    }
}