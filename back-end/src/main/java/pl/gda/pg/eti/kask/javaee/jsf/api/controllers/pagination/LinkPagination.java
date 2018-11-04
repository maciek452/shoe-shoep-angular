package pl.gda.pg.eti.kask.javaee.jsf.api.controllers.pagination;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;
import java.util.stream.Stream;

public class LinkPagination {

    public static final String PREV_REL = "prev";
    public static final String NEXT_REL = "next";
    public static final String FIRST_REL = "first";
    public static final String LAST_REL = "last";
    public static final String PAGE_QUERY_PARAM = "page";
    public static final int FIRST_PAGE = 1;

    public final int pageCount;
    public final int currentPageIndex;

    public LinkPagination(int currentPageIndex, int pageCount) {
        this.currentPageIndex = currentPageIndex;
        this.pageCount = pageCount;
    }

    public Stream<Link> toLinks(UriInfo uriInfo) {
        if (currentPageIndex == 1 && pageCount == 1) {
            return Stream.empty();
        }

        Stream.Builder<Link> linkStreamBuilder = Stream.builder();

        if (currentPageIndex > 1) {
            linkStreamBuilder.accept(
                    Link.fromUriBuilder(uriInfo.getRequestUriBuilder()
                            .replaceQueryParam(PAGE_QUERY_PARAM,
                                    currentPageIndex - 1))
                            .rel(PREV_REL)
                            .build());
        }

        if (currentPageIndex < pageCount) {
            linkStreamBuilder.accept(
                    Link.fromUriBuilder(uriInfo.getRequestUriBuilder()
                            .replaceQueryParam(PAGE_QUERY_PARAM,
                                    currentPageIndex + 1))
                            .rel(NEXT_REL)
                            .build());
        }

        linkStreamBuilder.accept(
                Link.fromUriBuilder(uriInfo.getRequestUriBuilder()
                        .replaceQueryParam(PAGE_QUERY_PARAM,
                                FIRST_PAGE))
                        .rel(FIRST_REL)
                        .build());

        linkStreamBuilder.accept(
                Link.fromUriBuilder(uriInfo.getRequestUriBuilder()
                        .replaceQueryParam(PAGE_QUERY_PARAM,
                                pageCount))
                        .rel(LAST_REL)
                        .build());

        return linkStreamBuilder.build();
    }
}
