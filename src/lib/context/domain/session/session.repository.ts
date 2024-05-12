import Session from './session.aggregate';

export default interface SessionRepository {
	create(session: Session): Promise<void>;
}
