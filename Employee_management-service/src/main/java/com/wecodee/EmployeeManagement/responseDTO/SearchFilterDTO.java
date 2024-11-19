package com.wecodee.EmployeeManagement.responseDTO;

public class SearchFilterDTO<T> {

	private T filterFieldValues;

	public T getFilterFieldValues() {
		return filterFieldValues;
	}

	public void setFilterFieldValues(T filterFieldValues) {
		this.filterFieldValues = filterFieldValues;
	}

	@Override
	public String toString() {
		return "SearchFilterDTO [filterFieldValues=" + filterFieldValues + "]";
	}

	
}
