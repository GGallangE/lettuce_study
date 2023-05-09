package lettuce.demo.Controller;


import lettuce.demo.Member.Member;
import lettuce.demo.Post.Post;
import lettuce.demo.Repository.MemberRepository;
import lettuce.demo.Repository.PostRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import java.util.Optional;


@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostController(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }


    @GetMapping("/create")
    public String postCreate() {

        return "Post/createPost";
    }
    @GetMapping("/lists")
    @PreAuthorize("isAuthenticated()")
    public String list(Model model){
        List<Post> postList = postRepository.findAllByOrderByCreateDateDesc();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<Member> findmember = memberRepository.findByEmail(authentication.getName());
        model.addAttribute("postList" , postList);
        model.addAttribute("member",findmember);
        return "Post/postList";
    }

    @GetMapping("/create/{memberId}")
    public String postCreate(@PathVariable Long memberId) {
        return "Post/createPost";
    }

    @PostMapping("/create")
    public String save(@Validated @ModelAttribute Post post , Model model, Principal principal) {
//        model.addAttribute("title", post.getTitle());
//        model.addAttribute("content", post.getContent());
        String username = principal.getName();
        Optional<Member> optionalMember = memberRepository.findByEmail(username);
//        Member member = (Member) memberRepository.findByName(username);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            post.setMember(member);
            postRepository.save(post);
            return "redirect:/posts/lists";
        } else {
            return "error-page";
        }

    }
    @GetMapping("/detail/{postId}")
    @PreAuthorize("isAuthenticated()")
    public String myPostDetail(@PathVariable Long postId, Model model) {
        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isPresent()) {
            model.addAttribute("post", findPost.get());
            model.addAttribute("nickname",findPost.get().getMember().getNickname());
            return "Post/detail";
        } else {
            return "redirect:/mypage/mylist";
        }
    }

}