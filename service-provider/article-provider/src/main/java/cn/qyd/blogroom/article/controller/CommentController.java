package cn.qyd.blogroom.article.controller;

import cn.qyd.blogroom.article.dto.CommentDto;
import cn.qyd.blogroom.article.entity.Comment;
import cn.qyd.blogroom.article.service.CommentService;
import cn.qyd.blogroom.article.vo.CommentVo;
import cn.qyd.blogroom.common.resp.Resp;
import cn.qyd.blogroom.common.utils.dozer.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author qyd
 * @Date 19-2-25 下午10:10
 */
@RestController
@RequestMapping("comment")
@Api(tags = "文章评论模块")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/save")
    @ApiOperation("用户添加评论")
    public Resp addComment(CommentDto commentDto) {
        Comment comment = commentService.save(commentDto);
        return Resp.succeed(comment);
    }

    @GetMapping("/all/{articleId}")
    @ApiOperation("获得某文章所有的评论")
    public Resp articleComments(@PathVariable("articleId")Long id) {
        List<Comment> comments = commentService.articleComments(id);
        List<CommentVo> commentVos = BeanMapper.mapList(comments, CommentVo.class);
        return Resp.succeed(commentVos);
    }

    @PutMapping("/update")
    @ApiOperation("修改评论")
    public Resp updateCommet(Long id,String content) {
        Boolean result = commentService.update(id, content);
        return Resp.succeed(result);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除评论")
    public Resp deleteCommet(Long id) {
        Boolean result = commentService.delete(id);
        return Resp.succeed(result);
    }

}
