/*
 * (c) 2013 panter llc, Zurich, Switzerland.
 */
package ch.bergturbenthal.filestore.jackson;

import java.io.File;
import java.io.IOException;

import ch.bergturbenthal.filestore.core.AbstractFileBackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 * TODO: add type comment.
 * 
 * @param <T>
 * 
 */
public class JacksonBackend<T> extends AbstractFileBackend<T> {

	private final static ObjectMapper mapper = new ObjectMapper();
	private static final String SUFFIX = ".json";
	static {
		mapper.registerModule(new JodaModule());
	}
	private final Class<T> type;

	/**
	 * @param basePath
	 * @param cacheWeight
	 *          TODO
	 * @param suffix
	 * @param serializer
	 */
	public JacksonBackend(final File basePath, final Class<T> type, final int cacheWeight) {
		super(basePath, SUFFIX, new AbstractFileBackend.FileSerializer<T>() {

			@Override
			public T readFromFile(final File f) throws IOException {
				return mapper.reader(type).readValue(f);
			}

			@Override
			public void writeToFile(final File f, final T value) throws IOException {
				mapper.writer().withDefaultPrettyPrinter().writeValue(f, value);
			}
		}, cacheWeight);
		this.type = type;
	}

	@Override
	public Class<T> getType() {
		return type;
	}
}
