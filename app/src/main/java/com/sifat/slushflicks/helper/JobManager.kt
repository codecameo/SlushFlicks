package com.sifat.slushflicks.helper

import android.util.Log
import kotlinx.coroutines.Job

class JobManager {
    private val jobs: HashMap<String, Job> = HashMap()

    fun addJob(tag: String, job: Job) {
        cancelJob(tag)
        jobs[tag] = job
    }

    fun cancelActiveJobs() {
        for ((tag, job) in jobs) {
            if (job.isActive) {
                job.cancel()
                Log.e(TAG, "job cancelling with tag: '$tag'")
            } else {
                Log.e(TAG, "job completed with tag: '$tag'")
            }
        }
    }

    private fun cancelJob(methodName: String) {
        getJob(methodName)?.run {
            if (isActive) cancel()
        }
    }

    private fun getJob(methodName: String): Job? {
        if (jobs.containsKey(methodName)) {
            jobs[methodName]?.let { job ->
                return job
            }
        }
        return null
    }

    companion object {
        private const val TAG = "JobManager"
    }
}