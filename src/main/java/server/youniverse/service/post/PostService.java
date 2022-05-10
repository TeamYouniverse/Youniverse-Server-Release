package server.youniverse.service.post;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.youniverse.common.exception.ErrorCode;
import server.youniverse.common.exception.model.NotFoundException;
import server.youniverse.common.exception.model.UnAuthorizedException;
import server.youniverse.controller.post.dto.request.RetrievePostByUsernameRequest;
import server.youniverse.domain.member.Member;
import server.youniverse.domain.member.MemberRepository;
import server.youniverse.domain.post.Post;
import server.youniverse.service.post.dto.response.PostDetailResponse;
import server.youniverse.domain.post.PostRepository;
import server.youniverse.service.post.dto.request.CreatePostRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static server.youniverse.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PostDetailResponse createNewtPost(CreatePostRequestDto request) {
        return PostDetailResponse.of(postRepository.save(request.toEntity()));
    }

    public List<PostDetailResponse> getPostsByUsername(RetrievePostByUsernameRequest request, Pageable pageable) {
        Member member = memberRepository.findByNickname(request.getNickname());

        return postRepository.findByMemberId(member.getId(), pageable)
                .stream()
                .map(post -> PostDetailResponse.of(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletePost(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(String.format("존재하지 않는 유저 (%s) 입니다.", memberId), NOT_FOUND_USER_EXCEPTION));
        if (member.getId() != memberId) {
            throw new UnAuthorizedException(String.format("접근할 수 없는 유저 (%s - %s) 의 요청입니다.", member.getId(), memberId));
        }
        postRepository.deleteById(postId);
    }
}