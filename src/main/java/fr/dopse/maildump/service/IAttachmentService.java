package fr.dopse.maildump.service;

import fr.dopse.maildump.model.AttachmentEntity;

/**
 * Created by fr27a86n on 11/04/2017.
 */
public interface IAttachmentService {

    AttachmentEntity findAttachmentById(Long id);

}
