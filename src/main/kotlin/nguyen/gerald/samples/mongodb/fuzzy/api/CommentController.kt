package nguyen.gerald.samples.mongodb.fuzzy.api

import nguyen.gerald.samples.mongodb.fuzzy.data.Comment
import nguyen.gerald.samples.mongodb.fuzzy.data.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentController {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    @GetMapping(value = [ "", "/" ])
    fun listAll(@RequestParam(value = "name", required = false) name: String?): List<Comment> {
        lateinit var result: Page<Comment>
        val page = Pageable.ofSize(10)

        if (name.isNullOrBlank()) {
            result = commentRepository.findAll(page)
        } else {
            result = commentRepository.findByNameContainingIgnoreCase(name, page)
        }

        return result.content
    }
}