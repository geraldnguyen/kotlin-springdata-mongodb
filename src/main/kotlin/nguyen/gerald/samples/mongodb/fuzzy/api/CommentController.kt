package nguyen.gerald.samples.mongodb.fuzzy.api

import nguyen.gerald.samples.mongodb.fuzzy.data.Comment
import nguyen.gerald.samples.mongodb.fuzzy.data.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    @GetMapping(value = [ "", "/" ])
    fun listAll(
        @RequestParam(value = "_size", required = false, defaultValue = "10") size: Int,
        @RequestParam(value = "_page", required = false, defaultValue = "0") page: Int
    ): List<Comment> {
        val pageable = Pageable.ofSize(size).withPage(page);

        val result: Page<Comment> = commentRepository.findAll(pageable)

        return result.content
    }

    @GetMapping(value = ["/{id}"] )
    fun findById(@PathVariable("id") id: String): Comment {
        return commentRepository.findById(id).get();
    }
}