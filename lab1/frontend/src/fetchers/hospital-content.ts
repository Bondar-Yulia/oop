import { fetchApi } from './generic';
import { Analysis } from '#types/models';

export async function createAnalysis(analysis: Omit<Analysis, 'id'>) {
    return fetchApi<number>('/analyses', { method: 'POST', body: analysis });
}

export async function updateAnalysis(analysis: Analysis) {
    return fetchApi<number>('/analyses', { method: 'PUT', body: analysis });
}

export async function getAnalyses() {
    return fetchApi<Analysis[]>('/analyses', { method: 'GET' });
}

export async function getAnalysis(id: string) {
    return fetchApi<Analysis>(`/analyses/${id}`, { method: 'GET' });
}

export async function deleteAnalysis(id: string) {
    return fetchApi<void>(`/analyses/${id}`, { method: 'DELETE' });
}
