import { ADD_SAMPLE, REMOVE_SAMPLE } from './types'

export const addSample = sample => ({
    type: ADD_SAMPLE,
    payload: sample
})

export const removeSample = sample => ({
    type: REMOVE_SAMPLE,
    sample
})
