package pl.gda.pg.eti.kask.javaee.jsf.business.entities;

import lombok.*;

import java.io.Serializable;
import java.net.URI;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Link implements Serializable {

    private URI uri;

    private String method;

    private String rel;
}
