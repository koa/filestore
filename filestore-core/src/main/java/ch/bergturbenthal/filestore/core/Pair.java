/*
 * (c) 2013 panter llc, Zurich, Switzerland.
 */
package ch.bergturbenthal.filestore.core;

import lombok.Data;

/**
 * TODO: add type comment.
 * 
 * @param <F>
 * @param <S>
 * 
 */
@Data
public class Pair<F, S> {
	public final F first;
	public final S second;
}
