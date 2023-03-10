package nguyen.gerald.samples.mongodb.fuzzy.api

import nguyen.gerald.samples.mongodb.fuzzy.data.Comment
import nguyen.gerald.samples.mongodb.fuzzy.data.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    @GetMapping(value = [ "", "/" ])
    fun listAll(
        @RequestParam(value = "email", required = false) email: String?,
        @RequestParam(value = "name", required = false) name: String?,
        @RequestParam(value = "keyword", required = false) keyword: String?,
        @RequestParam(value = "_size", required = false, defaultValue = "10") size: Int,
        @RequestParam(value = "_page", required = false, defaultValue = "0") page: Int
    ): List<Comment> {
        lateinit var result: Page<Comment>
        val pageable = Pageable.ofSize(size).withPage(page);

        when {
            !email.isNullOrBlank() ->
                result = commentRepository.findByEmailIgnoreCase(email, pageable)
            !name.isNullOrBlank() ->
                result = commentRepository.findByNameContainingIgnoreCase(name, pageable)
            !keyword.isNullOrBlank() ->
                result = commentRepository.findAllBy(TextCriteria.forDefaultLanguage().matching(keyword), pageable)
            else ->
                result = commentRepository.findAll(pageable)
        }

        return result.content
    }

    @GetMapping(value = ["/{id}"] )
    fun findById(@PathVariable("id") id: String): Comment {
        return commentRepository.findById(id).get();
    }
}