package nguyen.gerald.samples.mongodb.fuzzy.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document("comments")
class Comment {
    @Id
    lateinit var id: String

    lateinit var name: String

    lateinit var email: String

    @Field("movie_id")
    lateinit var movieId: String

    lateinit var text: String

    lateinit var date: LocalDateTime

}