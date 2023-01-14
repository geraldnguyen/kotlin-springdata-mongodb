package nguyen.gerald.samples.mongodb.fuzzy.data

import org.springframework.data.mongodb.repository.MongoRepository

interface CommentRepository : MongoRepository<Comment, String>