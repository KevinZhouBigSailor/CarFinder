package model;

import java.util.List;

public class SearchResult {
	private List<Status> statuses;
	private SearchMetadata search_metadata;

	public SearchMetadata getSearch_metadata() {
		return search_metadata;
	}

	public void setSearch_metadata(SearchMetadata search_metadata) {
		this.search_metadata = search_metadata;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

}
