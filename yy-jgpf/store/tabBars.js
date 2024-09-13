import { defineStore } from 'pinia';
import { commonApi } from '@/api/common-api';

const defaultTabBars = [
    { name: '推荐', id: 'tuijian' },
    { name: '建工', id: 'jiangong' },
    { name: '热点', id: 'redian' },
    { name: '财经', id: 'caijing' },
    { name: '娱乐', id: 'yule' },
    { name: '军事', id: 'junshi' },
    { name: '历史', id: 'lishi' },
    { name: '本地', id: 'bendi' },
];

export const useTabBarsStore = defineStore({
    id: 'tabBarsStore',
    state: () => ({
        tabBars: [...defaultTabBars],
    }),
    actions: {
        async fetchTabBars() {
            try {
                let response = await commonApi.getTabBars();
                this.tabBars = response.data;
            } catch (error) {
                console.error('Failed to fetch tabBars:', error);
            }
        },
    },
});