package gt.gob.rgm.inv.service;

import gt.gob.rgm.inv.domain.Principal;

public interface SecurityService {
	public Principal login(Principal principal);
}
