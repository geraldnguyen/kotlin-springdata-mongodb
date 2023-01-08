package nguyen.gerald.samples.mongodb.fuzzy.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello/")
class HelloController {

    @GetMapping(value = [ "", "/{name}" ])
    fun hello(@PathVariable(name = "name", required = false) name: String?): String {
        return "Hello ${name ?: "world"}"
    }
}