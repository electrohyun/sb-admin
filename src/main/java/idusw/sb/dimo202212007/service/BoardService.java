package idusw.sb.dimo202212007.service;


import idusw.sb.dimo202212007.domain.Board;
import idusw.sb.dimo202212007.entity.BoardEntity;
import idusw.sb.dimo202212007.entity.MemberEntity;

public interface BoardService {
    Long register(Board dto);   //Board : DTO or Domain
    // PageResultDTO<Board, Object[]> getList(PageRequestDTO pageRequestDTO);
    Board getById(Long bno);    //Id는 primary Key 값 @ID
    Long modify(Board dto);
    void update(Board board);
    void delete(Board board);
    int updateView(Long bno);


    default BoardEntity dtoToEntity(Board dto) {
        MemberEntity member = MemberEntity.builder()
                .id(Math.toIntExact(dto.getWriterId())) // Long -> Id 형변환
                .build();
        BoardEntity board = BoardEntity.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .views(dto.getViews())
                .writer(member)
                .build();

        return board;
    }

    default Board entityToDto(BoardEntity entity, MemberEntity member, Long replayCount) {
        Board dto = Board.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .views(entity.getViews())
                .block(entity.getBlock())
                .content(entity.getContent())
                .writerSeq(member.getMno())
                .writerId(member.getId())
                .writerName(member.getName())
                .writerEmail(member.getEmail())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .replyCount(replayCount.intValue())
                .build();
        return dto;
    }
}