package example

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import com.example.annotation.processor.auto.service.AutoService

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class SquabbleProcessor : AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val GENERATED_SUFFIX = "\$\$Args"
        private const val SERIALIZERS_SUFFIX = "\$\$Serializers"
        private const val ACTIVITY_ARGS_INTERFACE = "com.example.app.common.activity.ActivityArgs"
        private const val FRAGMENT_ARGS_INTERFACE = "com.example.app.common.base.FragmentArgs"
    }
}
