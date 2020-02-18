import { ADD_SAMPLE, REMOVE_SAMPLE } from '../samples/types'

const samples = (state = [], action) => {
    switch (action.type) {
        case ADD_SAMPLE: {
            const sample = action.payload
            if (!state.includes(sample)) {
                return [...state, sample]
            }
            return state
        }
        case REMOVE_SAMPLE: {
            const sample = action.payload
            if (state.includes(sample)) {
                return [...state, state.filter(s => s !== sample)]
            }
            return state
        }
        default:
            return state
    }
}

export default samples
