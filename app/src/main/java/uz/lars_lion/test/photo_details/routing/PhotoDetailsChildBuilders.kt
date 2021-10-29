package uz.lars_lion.test.photo_details.routing

import com.badoo.ribs.annotation.ExperimentalApi
import uz.lars_lion.test.photo_details.PhotoDetails

@ExperimentalApi
internal class PhotoDetailsChildBuilders(
    dependency: PhotoDetails.Dependency
) {
    private val subtreeDeps = SubtreeDependency(dependency)

    class SubtreeDependency(
        dependency: PhotoDetails.Dependency
    ) : PhotoDetails.Dependency by dependency
}



