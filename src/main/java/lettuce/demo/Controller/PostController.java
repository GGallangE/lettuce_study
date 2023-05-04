package lettuce.demo.Controller;


import lettuce.demo.Member.Member;
import lettuce.demo.Post.Post;
import lettuce.demo.Repository.MemberRepository;
import lettuce.demo.Repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostController(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/lists")
    public String list(Model model){
        List<Post> postList = postRepository.findAll();
        model.addAttribute("postList" , postList);
        return "Post/postList";
    }

    @GetMapping("/create")
    public String postCreate() {

        return "Post/createPost";
    }

    @PostMapping("/create")
    public String save(@Validated @ModelAttribute Post post , Model model, Principal principal) {
//        model.addAttribute("title", post.getTitle());
//        model.addAttribute("content", post.getContent());

        postRepository.save(post);
        return "redirect:/posts/lists";

    }


}