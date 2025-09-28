import { MailDTO } from './mail-dto.model';

export interface CampaignDTO {
  id: number;
  nom: string;
  description: string;
  contactsEmails: string[];
  mails: MailDTO[];
}
