import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog

object VideoDescLabelHooker : YukiBaseHooker() {
    override fun onHook() {
        val Label = "tv.danmaku.bili.videopage.data.view.model.BiliVideoDetail\$Label".toClass()
        val DescSection = "tv.danmaku.bili.ui.video.section.info.DescSection".toClass()
        val getLabelFromDesc =
                DescSection.method {
                    emptyParam()
                    returnType = Label
                }

        getLabelFromDesc.hook {
            after {
                YLog.debug("Label: ${reflectionToString(result)}")
                result = null
            }
        }
    }
}