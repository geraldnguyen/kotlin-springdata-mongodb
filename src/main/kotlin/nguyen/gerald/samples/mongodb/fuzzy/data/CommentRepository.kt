package nguyen.gerald.samples.mongodb.fuzzy.data

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<Comment, String>{
    fun findByNameContainingIgnoreCase(name: String, pageable: Pageable): Page<Comment>
}