package br.com.inkinvite.infrastructure.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@RegisterForReflection
public class ArquivoDto {
    @FormParam("arquivo")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] bytes;

}
